(ns ttt-clojure-ui.command-line-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure-ui.command-line :refer :all]))

(defn- make-input [inputs]
  (apply str (interleave inputs (repeat "\n"))))

(describe "Writing and reading in the command line"
  (it "Writes to the command line with new line"
    (should= "This message\n" (with-out-str (writeln "This message"))))
  (it "Reads from the command line and tries parsing it to integer"
    (should= 1 (with-in-str "1" (read-int))))
  (it "Asks a second time if bad input"
    (with-redefs [println (constantly nil)]
      (with-in-str (make-input '("a" 3))
        (should= 3 (read-int)))))
  (it "Asks to try again"
    (should-contain "input must be a number" (with-out-str 
                                              (with-in-str 
                                                (make-input '("a" 3)) (read-int)))))
  )
