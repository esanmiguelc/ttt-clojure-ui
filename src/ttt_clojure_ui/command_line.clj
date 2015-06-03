(ns ttt-clojure-ui.command-line)

(defn writeln [string]
  (println string))

(defn read-int []
  (try
    (Integer/parseInt (read-line))
    (catch Exception e 
      (do (writeln "Move must be a number, try again:")
          (read-int)))))

