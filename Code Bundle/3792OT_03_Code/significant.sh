#!/bin/sh

curl -XPOST 'localhost:9200/interns/review/1' -d '{"intern" : "Richard", "grade" : "bad", "type" : "grade"}'
curl -XPOST 'localhost:9200/interns/review/2' -d '{"intern" : "Ralf", "grade" : "perfect", "type" : "grade"}'
curl -XPOST 'localhost:9200/interns/review/3' -d '{"intern" : "Richard", "grade" : "bad", "type" : "grade"}'
curl -XPOST 'localhost:9200/interns/review/4' -d '{"intern" : "Richard", "grade" : "bad", "type" : "review"}'
curl -XPOST 'localhost:9200/interns/review/5' -d '{"intern" : "Richard", "grade" : "good", "type" : "grade"}'
curl -XPOST 'localhost:9200/interns/review/6' -d '{"intern" : "Ralf", "grade" : "good", "type" : "grade"}'
curl -XPOST 'localhost:9200/interns/review/7' -d '{"intern" : "Ralf", "grade" : "perfect", "type" : "review"}'
curl -XPOST 'localhost:9200/interns/review/8' -d '{"intern" : "Richard", "grade" : "medium", "type" : "review"}'
curl -XPOST 'localhost:9200/interns/review/9' -d '{"intern" : "Monica", "grade" : "medium", "type" : "grade"}'
curl -XPOST 'localhost:9200/interns/review/10' -d '{"intern" : "Monica", "grade" : "medium", "type" : "grade"}'
curl -XPOST 'localhost:9200/interns/review/11' -d '{"intern" : "Ralf", "grade" : "good", "type" : "grade"}'
curl -XPOST 'localhost:9200/interns/review/12' -d '{"intern" : "Ralf", "grade" : "good", "type" : "grade"}'