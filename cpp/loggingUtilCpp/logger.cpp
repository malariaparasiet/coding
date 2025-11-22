#include <iostream>
#include <fstream>
#include <string>

// Enum remains the same
enum logTypes {SUCCESS, ERROR, INFO};

class Logger {

private:
    std::string filePath;
    // 1. New private member to hold the file stream
    std::ofstream logFile; 
    
public:
    // Constructor now opens the file stream
    Logger(std::string filePath); 
    void LogToFile(int logType, std::string logString);
    // Destructor closes the file stream
    ~Logger(); 
};

// --- Logger Method Definitions ---

// 2. Constructor: Initialize filePath and OPEN the logFile stream
Logger::Logger(std::string filePath) {
    this->filePath = filePath;
    // Open the file. std::ios::app ensures new logs are APPENDED, not overwritten.
    logFile.open(filePath, std::ios::app); 
    if (!logFile.is_open()) {
        std::cerr << "Error: Could not open log file at " << filePath << std::endl;
    }
}

// 3. LogToFile: WRITE to the existing member stream
void Logger::LogToFile(int logType, std::string logString) {

    // Check if the file is open before attempting to write
    if (!logFile.is_open()) {
        std::cerr << "Error: Log file is not open for writing." << std::endl;
        return;
    }

    switch (logType) {
        case logTypes::SUCCESS:
            logFile << "✅ SUCCESS: " << logString << "\n";
            break;
        case logTypes::ERROR:
            logFile << "❌ ERROR: " << logString << "\n";
            break;
        case logTypes::INFO:
            logFile << "ℹ️ INFO: " << logString << "\n";
            break; // Added break for clarity, though not strictly necessary for the last case
    }
    // Flush the buffer to ensure the message is written immediately
    logFile.flush(); 
}

// 4. Destructor: Close the file stream when the Logger object is destroyed
Logger::~Logger() {
    if (logFile.is_open()) {
        logFile.close();
        std::cout << "Log file " << filePath << " closed." << std::endl;
    }
}


int main() {
    std::cout << "Testing logger functionality." << std::endl;
    
    // myFileLogger is created and the file is opened
    Logger myFileLogger("./logTest.log"); 
    
    myFileLogger.LogToFile(logTypes::INFO, "Logger initialized.");
    myFileLogger.LogToFile(logTypes::SUCCESS, "Operation completed successfully.");
    myFileLogger.LogToFile(logTypes::ERROR, "A critical error occurred.");
    
    std::cout << "All logs written." << std::endl;
    
    // When main() finishes, myFileLogger goes out of scope, 
    // its destructor (~Logger()) is called, and the file is closed.
    return 0; 
}