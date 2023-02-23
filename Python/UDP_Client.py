import socket

client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)


# client_socket.sendto(b"Give me a quote", ('localhost', 17)) # Send a request to the server on port 17
# data, server_address = client_socket.recvfrom(1024)
# quote = data.decode('utf-8').strip()
# print("Quote of the day:", quote)

while True:
    message = input("Enter a command (type 'exit' to quit): ")
    client_socket.sendto(message.encode('utf-8'), ('localhost', 17))
    if message == "exit":
        break
    data, server_address = client_socket.recvfrom(1024)
    quote = data.decode('utf-8').strip()
    print("Quote of the day:", quote)
    

client_socket.close()