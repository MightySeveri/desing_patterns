# Character progression game

A console game that uses the State design pattern for character levels. Each state controls the actions available at that level and the transition to the next level.

## Run

```sh
javac Main.java
java Main
```

Choose an action by its menu number. Enter `0` to quit.

## Rules

| Level | Available actions | Advance at |
| --- | --- | --- |
| Novice | Train | 100 total XP |
| Intermediate | Train, meditate | 250 total XP |
| Expert | Train, meditate, fight | 500 total XP |
| Master | Game complete | — |

Characters start with 0 XP and 100 HP. Training adds 50 XP. Meditation adds 20 HP. Fighting adds 100 XP and costs 25 HP. Experience thresholds are cumulative.
