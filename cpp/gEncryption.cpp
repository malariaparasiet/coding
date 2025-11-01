#include <iostream>
#include <limits>
#include "gEncryption.h"
#include <string>
#include <sstream>

const std::string logo = R"(
       ___________                                   __  .__               
   ____\_   _____/ ____   ___________ ___.__._______/  |_|__| ____   ____  
  / ___\|    __)_ /    \_/ ___\_  __ <   |  |\____ \   __\  |/  _ \ /    \ 
 / /_/  >        \   |  \  \___|  | \/\___  ||  |_> >  | |  (  <_> )   |  \
 \___  /_______  /___|  /\___  >__|   / ____||   __/|__| |__|\____/|___|  /
/_____/        \/     \/     \/       \/     |__|                       \/ 
)";

const std::string welcomeMessage = "Hello and welcome to this general encryption utility!";

const std::string optionMenu = R"(
[1]: xor cipher (value, key (key must be <= 255))
[2]: xor decipher (value, key (key must be <= 255))

Your choise: 
)";

int userOptionChoise, userIntValue, userKey = 0;
std::string userStringValue = "";
    
int main(int argc, char* argv[]) {
  std::cout << logo << std::endl;

  std::cout << welcomeMessage << std::endl;

  std::cout << optionMenu;

  while (!(std::cin >> userOptionChoise)) {
    std::cout << "Invalid input please input a number!" << std::endl;

    std::cin.clear();

    std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

    std::cout << "\nYour choise: ";
  }
  
  std::cin.ignore(std::numeric_limits<std::streamsize>::max(),'\n');

  switch (userOptionChoise) {
  case 1: {
        std::cout << "Please enter your value: ";

        std::cin >> userStringValue;

        std::stringstream ss(userStringValue);

        if (ss >> userIntValue && ss.peek() == EOF) {
        xorCipher(userIntValue, 23);
            }
        }
    }
}

void xorCipher(int value, int key) {
     int howMany255InValue;
     int restNumber;

     if (value >= 255) {
         howMany255InValue = value / 255;
         restNumber = value % 255;

         for (int i = 0; i < howMany255InValue; i++) {
             std::cout << static_cast<int>((static_cast<std::byte>(255) ^ static_cast<std::byte>(key))) << " ";
         }
         std::cout << static_cast<int>((static_cast<std::byte>(restNumber) ^ static_cast<std::byte>(key))) << std::endl;
     } else {
         std::cout << static_cast<int>((static_cast<std::byte>(value) ^ static_cast<std::byte>(key))) << std::endl;
     }
}