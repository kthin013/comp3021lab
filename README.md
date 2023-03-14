COMP3021LAB

     The source codes of all the labs of COMP3021 of HKUST

     TAM Yu Hin 20703622

-----------------------------------------------------

```diff
+ Code Review for Lab 03

! sortFolders() and sortNotes()


has nothing too special. Just call the Collections.sort and sortNotes in a for loop.

! searchNotes()

To achieve the case-insensitive, toLowerCase() is required.
The keywords are splited by "or" and stored in an array.
So all the string in the array will be stored in List<List<String>>

If the keyword is in AND condition, the string will be like "Lab attendance",
which will guarantee a space in it.
AND string will be split with " " in second string list.

 e.g.
          List ---> "java"
 List ---> List --> "lab" ---> "attendance"
          List ---> "session"

 Using for loop for(Note n:notes) to go through the List<List>, then checking it is OR or AND by the size of second list.
 If the note contains those keywords, then add a TRUE to List<boolean>.
 In the end of each loop, check whether List<boolean> contains TRUE, if true, then add(notes) else not.
