# Omid200716-AVA22-java2-slutprojekt-omid-nikzad
I detta projekt har jag skapat en "Produktionsregulator", en mjukvara som övervakar och reglerar produktion och konsumtion av enheter. Jag har designat mjukvaran som en desktop-applikation med ett grafiskt användargränssnitt (GUI).

    GUI: Jag har utvecklat ett användargränssnitt som ger en översikt över systemets status. Det inkluderar en progressbar som visar den aktuella kapaciteten i bufferten. Användaren kan lägga till eller ta bort arbetare (producenter) genom att klicka på knapparna "Lägg till Arbetare" och "Ta bort Arbetare". Jag har även implementerat en logg där aktiviteterna visas i realtid.

    Producenter (Workers): Jag har skapat producenter som genererar en enhet på ett slumpmässigt tidsintervall mellan 1-10 sekunder och placerar den i en delad buffer. Antalet producenter kan regleras av användaren via GUI.

    Konsumenter: Jag har skapat konsumenter vars antal är slumpmässigt valt mellan 3 och 15 vid start. Varje konsument tar slumpmässigt en enhet från bufferten varje 1-10 sekunder.

    Buffert: Jag har implementerat en buffert som fungerar som en delad resurs där producerade enheter lagras och konsumeras. Buffertens kapacitet övervakas och visas i GUI.

    Controller: Jag har skapat en controller som hanterar interaktionen mellan producenter, konsumenter och GUI. Den skapar trådar för producenter och konsumenter och övervakar buffertens status.

    Loggning: Jag har implementerat en loggfunktion där all aktivitet loggas och visas i realtid i GUI. Loggen innehåller information om antalet arbetare, produktionsintervall, förändringar i antalet arbetare, och när bufferten är för hög (>90%) eller för låg (<10%)
