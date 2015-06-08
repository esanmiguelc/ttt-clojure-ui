(ns ttt-clojure-ui.participants.human
  (:require [ttt-clojure-ui.command-line :as command-line :refer :all]
            [ttt-clojure-ui.messages :as messages :refer :all]
            [ttt-clojure-core.board :as board :refer :all]))

(defn take-turn [board]
  (writeln (messages/ask-make-move))
  (loop []
    (let [move (read-int)]
      (cond
        (or (>= 0 move) (< (count board) move))
        (do 
          (writeln (messages/out-of-range))
          (recur))
        (taken? board move) 
        (do 
          (writeln (messages/move-taken))
          (recur))
        :else move))))

