import socket
import datetime

def generate_quote():
    now = datetime.datetime.now()
    quote = "QOTD: {}".format(now) # Replace this with your own quote generation logic
    return quote

server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
server_socket.bind(('localhost', 17)) # Bind to port number 17 for QOTD protocol

while True:
    data, client_address = server_socket.recvfrom(1024)
    if data.decode('utf-8').strip() == "exit":
        break
    quote = generate_quote()
    server_socket.sendto(quote.encode('utf-8'), client_address)