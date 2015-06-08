(ns ttt-clojure.ui.messages-spec 
  (:require [speclj.core :refer :all]
            [ttt-clojure-ui.messages :refer :all]))

(describe "game strings"
  (it "has a welcome message"
    (should= "Welcome to a game of Tic Tac Toe!" (welcome)))

  (it "input must be a number"
    (should= "input must be a number, try again:" (invalid-input)))

  (it "move is out of range"
    (should= "Move out of range, try again: " (out-of-range)))

  (it "asks user to make a move"
    (should= "Please make a move: " (ask-make-move)))

  (it "move is taken"
    (should= "Move taken, try again: " (move-taken)))

  (it "lets user know who won"
    (should= "X Wins!" (display-winner "X")))

  (it "move too high"
    (should= "The move was too high!" (too-high)))

  (it "move too low"
    (should= "The move was too low!" (too-low)))

  (it "lets user know there was a tie"
    (should= "It's a tie!" (tie)))
  
  (it "asks the user to play again"
    (should= "Play again?\nType 1 for yes\nType 2 for no" (ask-play-again))))
