(ns ttt-clojure-ui.runner.game-loop-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure-ui.runner.game-loop :refer :all]))

(def first-player
  "X")

(def second-player
  "O")

(def tie-game
  ["X" "X" "O"
   "O" "X" "X"
   "X" "O" "O"])

(def x-winner
  ["X" "X" "O"
   "O" "X" "O"
   "X" "O" "X"])

(def o-winner
  ["X" "X" "O"
   "O" "O" "O"
   "X" "X"  9])

(defn- make-input [inputs]
  (apply str (interleave inputs (repeat "\n"))))

(describe "playing the game on console"
  (describe "end states of the game"
    (with-stubs)
    (it "ends the game with a tie"
      (should-contain "tie" (with-redefs [play-again? (stub :play-again?)] (with-out-str (run tie-game first-player second-player)))))
    (it "ends the game with X as winner"
      (should-contain "X Wins" (with-redefs [play-again? (stub :play-again?)] (with-out-str (run x-winner first-player second-player)))))
    (it "ends the game with 0 as winner"
      (should-contain "O Wins" (with-redefs [play-again? (stub :play-again?)] (with-out-str (run o-winner first-player second-player))))))

  (describe "playing again"
    (with-stubs)
    (it "asks the player to play again"
      (with-redefs [exit-game (stub :exit-game)]
      (should= "\nPlay again?\nType 1 for yes\nType 2 for no\n" (with-out-str (with-in-str "2" (play-again?))))))

    (it "returns true to play again"
        (with-out-str (should= true (with-in-str "1" (play-again?)))))

    (it "exits if player decides not to play"
        (with-out-str (should= false (with-in-str "2" (play-again?)))))

    (it "calls the play-again method"
      (with-redefs [play-again? (stub :play-again?)]
        (with-out-str (with-in-str "9" (run  ["X" "O" "X"
                                              "O" "X" "O"
                                              7   8   9] first-player second-player)))
        (should-have-invoked :play-again?)))

    (it "calls the play-again method on second player win" 
      (with-redefs [play-again? (stub :play-again?)]
        (with-out-str (with-in-str "7" (run  ["O" "X" "X"
                                              "O" "X" "O"
                                              7   8   "X"] first-player second-player)))
        (should-have-invoked :play-again?)))

    (it "calls the play-again method on tie" 
      (with-redefs [play-again? (stub :play-again?)]
        (with-out-str (with-in-str "8" (run  ["X" "O" "X"
                                              "X" "O" "O"
                                              "O"  8  "X"] first-player second-player)))
        (should-have-invoked :play-again?))))
  
  (describe "making a single move"
    (with-stubs)
    (it "winning the game"
      (let [stubbed-play-again (stub :play-again?)]
      (should-contain "X Wins" (with-redefs [play-again? stubbed-play-again] 
                      (with-out-str (with-in-str "9" (run  ["X" "O" "X"
                                                            "O" "X" "O"
                                                             7   8   9] first-player second-player)))))))

    (it "to tie"
      (let [stubbed-play-again (stub :play-again?)]
      (should-contain "tie" (with-redefs [play-again? stubbed-play-again]
                              (with-out-str (with-in-str "8" (run  ["X" "O" "X"
                                                                  "X" "O" "O"
                                                                  "O"  8  "X"] first-player second-player))))))))
  (describe "playing a whole game"
    (with-stubs)
    (it "plays the whole game against the computer for a tie"
      (should-contain "tie" (with-redefs [play-again? (stub play-again?)] 
                              (with-out-str 
                                (with-in-str 
                                  (make-input '(1 2 7 6 9)) 
                                  (run (vec (range 1 10)) first-player second-player))))))

  (it "plays the whole game against the computer for a loss"
    (should-contain "O Wins" (with-redefs [play-again? (stub play-again?)]
                               (with-out-str 
                                 (with-in-str 
                                   (make-input '(1 2 4)) 
                                   (run (vec (range 1 10)) first-player second-player)))))))
  )
