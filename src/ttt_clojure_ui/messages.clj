(ns ttt-clojure-ui.messages)

(defn welcome []
  "Welcome to a game of Tic Tac Toe!")

(defn ask-make-move []
  "Please make a move: ")

(defn display-winner [mark]
  (str mark " Wins!"))

(defn too-high []
  "The move was too high!")

(defn out-of-range []
  "Move out of range, try again: ")

(defn move-taken []
  "Move taken, try again: ")

(defn invalid-input []
  "input must be a number, try again:")

(defn too-low []
  "The move was too low!")

(defn tie []
  "It's a tie!")

(defn ask-play-again [] 
  (clojure.string/join "\n" '("Play again?" "Type 1 for yes" "Type 2 for no")))
