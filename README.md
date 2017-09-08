# transformation-company

Exposition:
- You are the Owner of a robot-battle stadium.
- You regularly host battles as your career in the entertainment industry (more revenue than the superbowl too).
- Entire Transformers league database: http://www.ntfa.net/ntfa/techspecs/index.php?cat=Gen1&group=AutoAN

How it Starts:
- There is a roster of transformers on 2 different teams stored in a list of objects.
- The roster is sorted by rank (quicksort).
- Every transformer pairs up with another of the opposiing team.
- They all battle: different stats give different outcomes, various transformers win/lose.

How it Ends:
- Whichever team has the most victories is the winner (the 2n+1'th wheel of the group didn't fight, if they are a loser then they are a survivor).
- Prints out: number of battles, winning team & competitors, survivors of the losing team (2n+1'th wheel)
- ***IMPORTANT NOTE: the chosen survivor depends on the pairing algorithm i.e.) it may vary across implementations***

--------------------------------------
**Sample Input:**

Soundwave, D, [8,9,2,6,7,5,6,10]

Bluestreak, A, [6,6,7,9,5,2,9,7]

Hubcap: A, [4,4,4,4,4,4,4,4]

--------------------------------------
**Sample Output:**

1 battles

Winning team (Decepticons): [Soundwave]

Survivors from the losing team (Autobots): [Bluestreak]
