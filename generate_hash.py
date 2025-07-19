import bcrypt

password = b"password"
hashed_password = bcrypt.hashpw(password, bcrypt.gensalt())
print(hashed_password.decode('utf-8'))