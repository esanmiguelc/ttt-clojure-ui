(ns ttt-clojure-ui.board-display
 (:require [ttt-clojure-core.board :as board :refer :all]))

(defn- stringify [board]
  (clojure.string/join " | " board))

(defn- add-divider [board]
  (clojure.string/join "\n--|---|-- \n" board))

(defn display-board [board]
  (add-divider (map stringify (board/split-board board))))

