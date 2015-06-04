(ns ttt-clojure-ui.core
  (:require [ttt-clojure-ui.runner.game-loop :as game-loop :refer :all]
            [ttt-clojure-ui.messages :as messages :refer :all]
            [ttt-clojure-ui.command-line :as ui :refer :all])
  (:gen-class))

(defn -main []
  (ui/writeln (messages/welcome))
  (loop [game (game-loop/run (vec (range 1 10)) "X" "O")]
    (when (= true game)
    (recur (game-loop/run (vec (range 1 10)) "X" "O")))))
