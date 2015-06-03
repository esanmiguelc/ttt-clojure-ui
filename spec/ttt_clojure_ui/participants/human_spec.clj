(ns ttt-clojure-ui.participants.human-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure-ui.participants.human :refer :all]))

(defn- make-input [inputs]
  (apply str (interleave inputs (repeat "\n"))))

(describe "Human"
  (it "should prompt the user"
    (should= "Please make a move: \n"(with-in-str "1" (with-out-str (take-turn (vec (range 1 10)))))))
  (it "prompts again when move is too low"
    (should-contain "Move out of range, try again: \n"(with-in-str (make-input '(0 1))  (with-out-str (take-turn (vec (range 1 10)))))))
  (it "prompts again when move is too high"
    (should-contain "Move out of range, try again: \n"(with-in-str (make-input '(10 1))  (with-out-str (take-turn (vec (range 1 10)))))))
  (it "prompts again when move is taken"
    (should-contain "Move taken, try again: \n"(with-in-str (make-input '(1 2))  (with-out-str (take-turn  ["X" 2 3
                                                                                                              4 5 6
                                                                                                              7 8 9])))))
  )
