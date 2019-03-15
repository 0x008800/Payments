#!/bin/bash
while true;
do
curl -X POST localhost:8080/payment/create -H "Content-type: application/json" -d '{"nameOfSender": "name", "innofSender": "12345", "numberOfCardOfSender": "132", "accauntOfReceiver": "888", "mfoofReceiver": "987", "okpoofReceiver": "765", "nameOfReceiver": "receiver1", "period": "60", "count": "100"}';

done
