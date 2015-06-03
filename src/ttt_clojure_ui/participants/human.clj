(ns ttt-clojure-ui.participants.human
  (:require [ttt-clojure-ui.command-line :as command-line :refer :all]
            [ttt-clojure-core.board :as board :refer :all]))

(defn take-turn [board]
  (writeln "Please make a move: ")
  (loop []
    (let [move (read-int)]
      (cond
        (or (>= 0 move) (< (count board) move))
        (do 
          (writeln "Move out of range, try again: ")
          (recur))
        (taken? board move) 
        (do 
          (writeln "Move taken, try again: ")
          (recur))
        :else move))))

