#ifndef GENCRYPTION_H
#define GENCRYPTION_H

#include <string>

void startupSequence();

void getUserInput();

void xorCipher(int value, int key);

void xorCipher(std::string value, std::string key);

void xorDecipher(int value, int key);

void xorDecipher(std::string value, std::string key);

#endif