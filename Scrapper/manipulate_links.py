import json
#This program generates a json file with the dictionary of links that the spider creates 
links = [
    "copy here the results of the spider that gets the links"
]

new_dictionary = {}
for dictionary in links:
    new_dictionary.setdefault('link_to_game',[]).extend(dictionary['link_to_game'])

with open('links.json', 'w') as archivo:   
    json.dump(new_dictionary, archivo)

