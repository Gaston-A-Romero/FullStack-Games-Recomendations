#This program creates the final json file with all the games data with a proper format
import json
from datetime import datetime

filename = "games.json" 

with open(filename,"rb") as f:
    games = json.load(f)

final_data = []
for item in games:  
    item["genres"] = list(set(item["genres"])) 
    if(item["release_date"] == 'TBA' or item["release_date"] == 'TBA - Early Access'):
        release_date = 'Not Released'
        year_release = 0
    
    else:
        date_obj = datetime.strptime(item["release_date"], '%b %d, %Y')
        release_date = date_obj.strftime('%b %d')
        year_release = date_obj.year

    genres = []   
    for genero in item['genres']:
        actual_genres = []
        actual_genres.append(genero)
        genre = {'genre_name': genero}             
        genres.append(genre)
        
            
    new_dict = {
        "title": item["title"],
        "platform": item["platform"],
        "company": item["company"],
        "release_date": release_date,
        "year_release": year_release,
        "developer": item["developer"],
        "genres": genres,
        "picture": item["picture"]
    }
    final_data.append(new_dict)

      
with open('games_data.json', 'w') as outfile:
    json.dump(final_data, outfile)
    


