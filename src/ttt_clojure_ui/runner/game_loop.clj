(ns ttt-clojure-ui.runner.game-loop
  (:require [ttt-clojure-core.rules :as rules :refer :all]
            [ttt-clojure-core.runner.game-runner :as runner :refer :all]
            [ttt-clojure-ui.board-display :as board-display :refer :all]
            [ttt-clojure-ui.command-line :as ui :refer :all]
            [ttt-clojure-ui.messages :as messages :refer :all]
            [ttt-clojure-core.board :as board :refer :all]
            [ttt-clojure-core.participants.hard-ai :as hard :refer :all]
            [ttt-clojure-ui.participants.human :as human :refer :all]))


(defn- get-player-move [board]
  (human/take-turn board))

(defn- get-hard-ai-move [board]
  (writeln "\n")
  (hard/get-move board "O" "X"))

(defn- get-easy-ai-move [board]
  (dec (rand-nth (board/available-moves board))))

(defn exit-game [exit-code]
  (System/exit exit-code))

(defn play-again? [] 
  (writeln "")
  (writeln (messages/ask-play-again))
  (let [decision (read-int)]
    (if (= decision 1) true false)))

(defn run [board first-participant second-participant]
  (loop [board board]
    (ui/writeln (board-display/display-board board))
    (cond
      (rules/winner? board first-participant) (do 
                                                (ui/writeln (messages/display-winner first-participant))
                                                (play-again?))
      (rules/winner? board second-participant) (do 
                                                (ui/writeln (messages/display-winner second-participant))
                                                (play-again?))
      (board/board-full? board) (do (ui/writeln (messages/tie))
                                    (play-again?))
      :else
       (if (= first-participant (rules/current-participant? board))
         (recur (runner/play board first-participant second-participant (list (get-player-move board))))
         (recur (runner/play board first-participant second-participant (list (get-hard-ai-move board))))
         ))))
