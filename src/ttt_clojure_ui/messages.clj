(ns ttt-clojure-ui.messages)

(defn welcome []
  (str "Welcome to a game of Tic Tac Toe!"))

(defn ask-make-move []
  (str "Please make a move:"))

(defn display-winner [mark]
  (str mark " " "Wins!"))

(defn too-high []
  "The move was too high!")

(defn too-low []
  "The move was too low!")

(defn tie []
  (str "It's a tie!"))

(defn ask-play-again [] 
  (clojure.string/join "\n" '("Play again?" "Type 1 for yes" "Type 2 for no")))
