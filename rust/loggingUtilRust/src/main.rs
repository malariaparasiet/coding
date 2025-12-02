mod loggingUtility {
    use std::fs::OpenOptions;
    use std::io::prelude::*;
    use chrono;


    pub enum LoggingTypes {
        SUCCES,
        FAILURE,
        INFO
    }

    pub fn logToLogFile(logTypes: LoggingTypes, logMsg: String) -> std::io::Result<()> {
        let mut file = OpenOptions::new().append(true).create(true).open("log.log")?;
        
        let formatted_msg = match logTypes {
            LoggingTypes::SUCCES  => format!("{} ✅ SUCCES: {}\n", chrono::offset::Local::now() ,logMsg),
            LoggingTypes::FAILURE => format!("{} ❌ FAILURE: {}\n", chrono::offset::Local::now() ,logMsg),
            LoggingTypes::INFO    => format!("{} ℹ️ INFO: {}\n", chrono::offset::Local::now() ,logMsg),
        };

        file.write_all(formatted_msg.as_bytes())?;

        return Ok(());
    }
}

use loggingUtility::*;

fn main() -> std::io::Result<()> {

    logToLogFile(LoggingTypes::SUCCES, "Yippie it worked!".to_string())?;
    logToLogFile(LoggingTypes::FAILURE, "Awh it did not work!".to_string())?;
    logToLogFile(LoggingTypes::INFO, "This is just some basic information!".to_string())?;

    return Ok(());
}