Create a playlist of your favorite songs. Create a shuffle of your songs (permutation of your original songs).

Use the Fisher–Yates shuffle algorithm that works in O(n) running time. 

The basic idea is to start from the last element, swap it with a randomly selected element from the whole array (including last). 

In the next step you will consider the array from 0 to n-2 (size reduced by 1), and repeat the process until you reach the first element. 

Write a program that uses the provided Playlist.txt as input and outputs the shuffled array in a file called LastNameFirstNamePlaylist.txt.
