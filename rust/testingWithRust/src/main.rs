fn main() {
    let text = "not a number";
    let result = text.parse::<i32>();
    
    // Read as: "If the 'result' matches the 'Err(error)' pattern..."
    if let Err(error) = result {
        // ...then create a new variable 'error' and run this block.
        println!("Caught an error: {:?}", error);
    }
}
