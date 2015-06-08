(ns ttt-clojure-ui.command-line
  (:require [ttt-clojure-ui.messages :as messages :refer :all]))

(defn writeln [string]
  (println string))

(defn read-int []
  (try
    (Integer/parseInt (read-line))
    (catch Exception e 
      (do (writeln (messages/invalid-input))
          (read-int)))))

