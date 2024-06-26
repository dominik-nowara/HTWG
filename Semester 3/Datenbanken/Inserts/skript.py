import random

countries = ["Austria", "Germany", "Spain", "Italy"]
names_list = ["Alice", "Anna", "Alex", "Bob", "Bella", "Ben", "Cathy", "Chris", "Celine", "David", "Diana", "Dylan", "Eva", "Erik", "Fiona", "Frank", "Fiona", "Frank", "Gina", "George", "Gina", "George", "Hannah", "Hans", "Hannah", "Hans", "Iris", "Ivan", "Iris", "Ivan", "Julia", "John", "Julia", "John", "Kathy", "Karl", "Kathy", "Karl", "Lena", "Lars", "Lena", "Lars", "Mia", "Max", "Mia", "Max", "Nina", "Nick", "Nina", "Nick", "Olivia", "Oscar", "Olivia", "Oscar", "Paula", "Paul", "Paula", "Paul", "Quinn", "Quentin", "Quinn", "Quentin", "Rita", "Ralf", "Rita", "Ralf", "Sara", "Sam", "Sara", "Sam", "Tina", "Tom", "Tina", "Tom", "Ursula", "Uwe", "Ursula", "Uwe", "Vera", "Vince", "Vera", "Vince", "Wendy", "Will", "Wendy", "Will", "Xenia", "Xander", "Xenia", "Xander", "Yvonne", "Yannick", "Yvonne", "Yannick", "Zoe", "Zack", "Zoe", "Zack"]
surnames = ["Adams", "Anderson", "Adams", "Baker", "Brown", "Carter", "Clark", "Carter", "Davis", "Davies", "Edwards", "Evans", "Fisher", "Foster", "Garcia", "Gonzalez", "Harris", "Hughes", "Ivanov", "Ivanova", "Jensen", "Johansen", "Kovacs", "Lopez", "Muller", "Nagy", "Olsen", "Perez", "Quinn", "Ramos", "Smith", "Taylor", "Ullrich", "Varga", "Williams", "Xu", "Yilmaz", "Zhang"]
zip_codes = {
    "Vienna": 1010,
    "Graz": 8010,
    "Linz": 4020,
    "Salzburg": 5020,
    "Innsbruck": 6020,
    "Klagenfurt": 9020,
    "Villach": 9500,
    "Wels": 4600,
    "Sankt Pölten": 3100,
    "Dornbirn": 6850,
    "Wiener Neustadt": 2700,
    "Steyr": 4400,
    "Feldkirch": 6800,
    "Bregenz": 6900,
    "Wolfsberg": 9400,
    "Baden": 2500,
    "Krems": 3500,
    "Leoben": 8700,
    "Leonding": 4060,
    "Traun": 4050,
    "Amstetten": 3300,
    "Kapfenberg": 8605,
    "Lustenau": 6890,
    "Mödling": 2340,
    "Hallein": 5400,
    "Kufstein": 6330,
    "Traiskirchen": 2514,
    "Schwechat": 2320,
    "Braunau am Inn": 5280,
    "Stockerau": 2000,
    "Saalfelden am Steinernen Meer": 5760,
    "Ansfelden": 4052,
    "Tulln": 3430,
    "Hohenems": 6845,
    "Spittal an der Drau": 9800,
    "Telfs": 6410,
    "Ternitz": 2630,
    "Perchtoldsdorf": 2380,
    "Feldkirchen in Kärnten": 9560,
    "Bludenz": 6700,
    "Bad Ischl": 4820,
    "Eisenstadt": 7000,
    "Schwaz": 6130,
    "Hall in Tirol": 6060,
    "Gmunden": 4810,
    "Wörgl": 6300,
    "Wals-Siezenheim": 5071
}
streets = ["Main Street", "High Street", "Park Avenue", "Maple Street", "Church Street", "Elm Street", "Washington Street", "South Street", "Lake Street", "Mill Road", "Main Street", "High Street", "Park Avenue", "Maple Street", "Church Street", "Elm Street", "Washington Street", "South Street", "Lake Street", "Mill Road", "Main Street", "High Street", "Park Avenue", "Maple Street", "Church Street", "Elm Street", "Washington Street", "South Street", "Lake Street", "Mill Road", "Main Street", "High Street", "Park Avenue", "Maple Street", "Church Street", "Elm Street", "Washington Street", "South Street", "Lake Street", "Mill Road", "Main Street", "High Street", "Park Avenue", "Maple Street", "Church Street", "Elm Street", "Washington Street", "South Street", "Lake Street", "Mill Road", "Main Street", "High Street", "Park Avenue", "Maple Street", "Church Street", "Elm Street", "Washington Street", "South Street", "Lake Street", "Mill Road", "Main Street", "High Street", "Park Avenue", "Maple Street", "Church Street", "Elm Street", "Washington Street", "South Street", "Lake Street", "Mill Road", "Main Street", "High Street", "Park Avenue", "Maple Street", "Church Street", "Elm Street", "Washington Street", "South Street", "Lake Street", "Mill Road"]
apartment_names = ["Apartment", "Holiday Home", "Cottage", "Villa", "House", "Flat", "Studio", "Loft", "Penthouse", "Bungalow", "Chalet", "Mansion", "Castle", "Farmhouse", "Boat", "Yacht", "Treehouse", "Cabin", "Caravan", "Tent", "Igloo", "Tipi", "Lighthouse", "Cave", "Barn", "Dome", "Hut", "Hostel", "Hotel", "Motel", "Inn", "Resort", "Lodge", "Guesthouse", "Bed and Breakfast", "Camping", "Glamping", "Campervan", "RV", "Trailer", "Houseboat", "Yurt", "Wigwam", "Gypsy Wagon", "Shepherds Hut", "Tiny House", "Container", "Bunker", "Castle", "Palace", "Fortress", "Tower", "Keep", "Citadel", "Stronghold", "Bastion", "Rampart", "Moat", "Bailey", "Donjon", "Barbican", "Curtain Wall", "Gatehouse", "Portcullis", "Drawbridge", "Machicolation", "Battlement", "Arrow", "Slit", "Turret", "Merlon", "Parapet", "Crenel", "Embrasure", "Sally Port", "Ravelin", "Counterscarp", "Scarp", "Glacis", "Fosse", "Escarpment", "Redoubt", "Bulwark", "Ravelin", "Hornwork", "Sconce", "Bastion", "Caponier", "Counterguard", "Tenaille", "Lunette", "Redan", "Hornwork"]
equipment = ["Sauna", "Pool", "Jacuzzi", "Balcony", "Terrace", "Garden", "Barbecue", "Fireplace", "Air Conditioning", "Heating", "TV", "WiFi", "Kitchen", "Fridge", "Oven", "Microwave", "Coffee Machine", "Dishwasher", "Washing Machine", "Dryer", "Iron", "Hairdryer", "Towels", "Bed Linen", "Toiletries", "First Aid Kit", "Fire Extinguisher"]


countrySql = "SET TRANSACTION READ WRITE;\nbegin\n\n"
for country in countries:
    countrySql += f"INSERT INTO land (laendername) VALUES ('{country}');\n"
countrySql += "commit;\nend;"
with open("1countries.sql", "w") as file:
    file.write(countrySql)

adressSql = "SET TRANSACTION READ WRITE;\nbegin\n\n"
for currently in range(1000):
    random_number = random.randint(0, len(zip_codes) - 1)
    city = list(zip_codes.keys())[random_number]
    zip_code = zip_codes[city]

    random_number2 = random.randint(0, 50)
    random_letter_possiblity = random.randint(0, 10)
    random_house_number = random_number2
    if random_letter_possiblity == 10:
        random_house_number = f"{random_number2}a"

    adressSql += f"INSERT INTO adresse (adress_id, strasse, hausnummer, postleitzahl, stadt, laendername)  VALUES ({currently + 1}, '{random.choice(streets)}', '{random_letter_possiblity}', {zip_code}, '{city}', '{random.choice(countries)}');\n"

adressSql += "commit;\nend;"
with open("2adresses.sql", "w") as file:
    file.write(adressSql)
    

apartment_ids = []
for currently in range(200):
    random_number = random.randint(1, 1000)
    while random_number in apartment_ids:
        random_number = random.randint(1, 1000)
    apartment_ids.append(random_number)

apartmentSQL = "SET TRANSACTION READ WRITE;\nbegin\n\n"
for currently in range(200):
    apartmentSQL += f"INSERT INTO ferienwohnung (wohnungsname, zimmer, groesse, preis, wohnungs_id, adress_id) VALUES ('{random.choice(apartment_names)}', {random.randint(1, 10)}, {random.randint(10, 200)}, {random.randint(100, 20000)}, {currently + 1}, {apartment_ids[currently]});\n"
apartmentSQL += "commit;\nend;"
with open("3apartments.sql", "w") as file:
    file.write(apartmentSQL)


copied_equipment = equipment.copy()
equipmentSQL = "SET TRANSACTION READ WRITE;\nbegin\n\n"
for currently in equipment:
    equipmentSQL += f"INSERT INTO ausstattung (ausstattungsname) VALUES ('{currently}');\n"
equipmentSQL += "commit;\nend;"
with open("4equipments.sql", "w") as file:
    file.write(equipmentSQL)

containedequipmentSQL = "SET TRANSACTION READ WRITE;\nbegin\n\n"
for currently in range(200):
    random_number = random.randint(1, 5)
    random_equipment = random.sample(copied_equipment, random_number)

    for equipment in random_equipment:
        containedequipmentSQL += f"INSERT INTO beinhaltete_ausstattung (wohnungs_id, ausstattungsname) VALUES ({currently + 1}, '{equipment}');\n"

containedequipmentSQL += "commit;\nend;"
with open(f"5containedequipment.sql", "w") as file:
    file.write(containedequipmentSQL)

# Give me a list with 200 random attraction names. Don't include safaris
attraction_names = ["Museum", "Zoo", "Aquarium", "Amusement Park", "Theme Park", "Water Park", "Botanical Garden", "National Park", "Nature Reserve", "Sculpture Park", "Wildlife Sanctuary", "Observatory", "Planetarium", "Science Center", "Art Gallery", "Historic Site", "Monument", "Castle", "Palace", "Fortress", "Tower", "Keep", "Citadel", "Stronghold", "Bastion", "Rampart", "Moat", "Bailey", "Donjon", "Barbican", "Curtain Wall", "Gatehouse", "Portcullis", "Drawbridge", "Machicolation", "Battlement", "Arrow", "Slit", "Turret", "Merlon", "Parapet", "Crenel", "Embrasure", "Sally Port", "Ravelin", "Counterscarp", "Scarp", "Glacis", "Fosse", "Escarpment", "Redoubt", "Bulwark", "Hornwork", "Sconce", "Caponier", "Counterguard", "Tenaille", "Lunette", "Redan"]

attractionSQL = "SET TRANSACTION READ WRITE;\nbegin\n\n"
for attraction in attraction_names:
    attractionSQL += f"INSERT INTO attraktion (attraktionsname) VALUES ('{attraction}');\n"

attractionSQL += "commit;\nend;"
# write the sql to a file
with open("6attractions.sql", "w") as file:
    file.write(attractionSQL)

attranctiondistanceSQL = "SET TRANSACTION READ WRITE;\nbegin\n\n"
for ferienwohnung in range(200):
    random_number = random.randint(1, 5)
    random_attraction = random.sample(attraction_names, random_number)

    for attraction in random_attraction:
        random_number2 = random.randint(1, 49)
        attranctiondistanceSQL += f"INSERT INTO attraktions_entfernung (wohnungs_id, attraktionsname, kilometer) VALUES ({ferienwohnung + 1}, '{attraction}', {random_number2});\n"

attranctiondistanceSQL += "commit;\nend;"
# write the sql to a file
with open("7attractiondistance.sql", "w") as file:
    file.write(attranctiondistanceSQL)

# Generate me a list with 100 random mails. The mail should contain the name of the person, an @ and a random domain. Use the names from the beginning.
mails = []
for names in surnames:
    mails.append(f"{names}@{random.choice(['gmail.com', 'yahoo.com', 'hotmail.com', 'outlook.com', 'aol.com', 'gmx.net', 'web.de', 't-online.de', 'mail.com', 'icloud.com'])}")

customerSQL = "SET TRANSACTION READ WRITE;\nbegin\n\n"
for i in range(len(mails)):
    # Generate a random password
    random_password = ""
    for currently in range(10):
        random_password += random.choice("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789")

    # Select a random name and surname
    random_name = random.choice(names_list)

    # Generate a random 22 lon iban number
    random_iban = ""
    for currently in range(22):
        random_iban += random.choice("0123456789")

    customerSQL += (f"INSERT INTO kunde (mail, passwort, kundenname, iban, adress_id) VALUES ('{mails[i]}', '{random_password}', "
                    f"'{random_name} {surnames[i]}', '{random_iban}', {i + 1});\n")

customerSQL += "commit;\nend;"
# write the sql to a file
with open("8customers.sql", "w") as file:
    file.write(customerSQL)

money = []
# Generate random bookings. The booking should look like this: INSERT INTO buchung (buchungsnummer, startdatum, enddatum, rechnungsnummer, betrag, wohnungs_id, mail). A date should have the format TO_DATE('2024/05/03 21:00:00', 'yyyy/mm/dd hh24:mi:ss')
bookingSQL = "SET TRANSACTION READ WRITE;\nbegin\n\n"
for currently in range(200):
    # Generate a random date
    random_month = random.randint(1, 10)
    random_year = random.randint(2020, 2024)
    random_date = f"TO_DATE('{random_year}/{random_month}/{random.randint(1, 28)} {random.randint(0, 23)}:{random.randint(0, 59)}:{random.randint(0, 59)}', 'yyyy/mm/dd hh24:mi:ss')"

    # Generate a random date that is bigger than the first date
    random_date2 = f"TO_DATE('{random_year}/{random_month + 1}/{random.randint(1, 28)} {random.randint(0, 23)}:{random.randint(0, 59)}:{random.randint(0, 59)}', 'yyyy/mm/dd hh24:mi:ss')"

    # Generate a random date that is bigger than the first date
    random_date3 = f"TO_DATE('{random_year}/{random_month + 2}/{random.randint(1, 28)} {random.randint(0, 23)}:{random.randint(0, 59)}:{random.randint(0, 59)}', 'yyyy/mm/dd hh24:mi:ss')"

    stars = random.randint(1, 5)

    # Generate a random number
    random_number = random.randint(1, 1000)

    # Generate a random price
    random_price = random.randint(100, 20000)
    money.append(random_price)

    # Generate a random number
    random_number2 = random.randint(1, 200)

    # Generate a random mail
    random_mail = random.choice(mails)

    bookingSQL += f"INSERT INTO buchung (buchungsnummer, startdatum, enddatum, rechnungsnummer, betrag, bewertungsdatum, bewertungssterne, wohnungs_id, mail) VALUES ({currently + 1}, {random_date}, {random_date2}, {random_number}, {random_price}, {random_date3}, {stars}, {random_number2}, '{random_mail}');\n"

bookingSQL += "commit;\nend;"
# write the sql to a file
with open("91bookings.sql", "w") as file:
    file.write(bookingSQL)

# Generate 45 down payments. They should look like this: INSERT INTO anzahlung (anzahlungs_id, geldbetrag, zahlungsdatum, buchungsnummer). A date should have the format TO_DATE('2024/05/03 21:00:00', 'yyyy/mm/dd hh24:mi:ss'). The buchungsnummer should be a random number between 1 and 60
downpaymentSQL = "SET TRANSACTION READ WRITE;\nbegin\n\n"
for currently in range(45):
    # Generate a random number. The number should be between 1 and money[currently]
    random_number = random.randint(1, money[currently])

    # Generate a random date
    random_date = f"TO_DATE('{random.randint(2020, 2024)}/{random.randint(1, 12)}/{random.randint(1, 28)} {random.randint(0, 23)}:{random.randint(0, 59)}:{random.randint(0, 59)}', 'yyyy/mm/dd hh24:mi:ss')"

    # Generate a random number
    random_number2 = random.randint(1, 60)

    downpaymentSQL += f"INSERT INTO anzahlung (anzahlungs_id, geldbetrag, zahlungsdatum, buchungsnummer) VALUES ({currently + 1}, {random_number}, {random_date}, {random_number2});\n"

downpaymentSQL += "commit;\nend;"
# write the sql to a file
with open("92downpayments.sql", "w") as file:
    file.write(downpaymentSQL)
