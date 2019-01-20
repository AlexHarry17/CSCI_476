file = open('text_files/input.txt')  # opens input file
encrypted_file = file.read()

alphabet = 'abcdefghijklmnopqrstuvwxyz'
decrypt_place_holder = 12  # place holder to used in decryption formula
key = {}  # dictionary for decyphered key values


def add_key(decrypt_key, value):
    key.update({decrypt_key: value})
    key.update({value: decrypt_key})


for letter in alphabet:  # loops through the alphabet
    if decrypt_place_holder is 0:
        if letter not in key:
            # adds keys to dictionary
            add_key(letter, (chr(ord(letter) + decrypt_place_holder)))
        decrypt_place_holder = 12  # resets place holder variable
    else:
        if letter not in key:
            add_key(letter, (chr(ord(letter) + decrypt_place_holder)))
            decrypt_place_holder -= 2  # subtracts 2 from place holder

output_file = open('text_files/output.txt', 'w')  # opens output file, makes it writable.

decyphered_output = ""
for i in encrypted_file:
    if ord(i) > 96 and ord(i) < 123:  # makes sure character is a lowercase letter, checks ascii value.
        decyphered_output += key.get(i)  # Gets value from key, writes to file.
    else:
        decyphered_output += i  # writes value that isn't a lower case letter. IE ",", " "

output_file.write(decyphered_output)
# CLoses files.
file.close()
output_file.close()
