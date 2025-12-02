#include <string>
#include <iostream>

class Person {
    private:
        std::string firstName;

    public:
        void setFirstName(std::string firstName);
        std::string getFirstName();
        Person(std::string firstName);
        ~Person();
};

Person::Person(std::string firstName) {
    this->firstName = firstName;
}

Person::~Person() {
    std::cout << "Destructor called! \n";
}

void Person::setFirstName(std::string firstName) {
    this->firstName = firstName;
}

std::string Person::getFirstName() {
    return this->firstName;
}

int main() {
    Person klaas("Johannetje");
    std::cout << klaas.getFirstName() << std::endl;
}