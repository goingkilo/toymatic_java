# pip3 install inflect
import inflect
import json

# gen keys
p = inflect.engine()
tokens = [p.number_to_words(x) for x in range(1,82)]

#parse text
t = open('ttc.txt').read().split('\n')
a = {}
b = []

token = None
for i in t:
    if i.lower() in tokens:
        if token:
            b.pop(0)
            a[token.lower()] = ''.join(b.copy())
            b = []
        token = i
    b.append(i)



t.close()

# save output
io = open('ttc.json','w')
json.dump( a, io, indent=4)
io.close()