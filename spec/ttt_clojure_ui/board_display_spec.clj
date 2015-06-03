(ns ttt-clojure.ui.board-display-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure-ui.board-display :refer :all]))

(describe "displaying the board on the command line"
  (it "displays a 3x3 board"
    (should= "1 | 2 | 3\n--|---|-- \n4 | 5 | 6\n--|---|-- \n7 | 8 | 9" (display-board (vec (range 1 10))))))
