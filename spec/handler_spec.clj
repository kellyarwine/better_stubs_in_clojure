(ns handler-spec
  (:require [speclj.core :refer :all]
            [handler :refer :all]))

(describe "merge"
  (it "should merge data from two strings into a single vector"
    (should= ["a" "b" "c" "d" "e"] (merge ["a" "b"] ["c" "d" "e"]))))

(describe "save"
    (it "should save the data"
      (should= "SAVED:a" (save "a"))))

; ### Approach 1 ###
(describe "handle-data"
  (it "should make sure things are wired correctly"
    (with-redefs [merge (fn [_ _] ["1" "2"])
                  save  (fn [_] "saved")]
      (should= ["saved" "saved"] (handle-data ["a"] ["b"])))))

; ### Approach 2 ###
(describe "handle-data"
  (it "should make sure things are wired correctly"
    (let [merged (atom nil)
          saved  (atom nil)]
      (with-redefs [merge (fn [a1 a2] (reset! merged (str "m" a1 "m" a2)) @merged)
                    save  (fn [a3] (reset! saved (str @saved "SAVED:" a3)))]
        (handle-data "a" "b")
        (should= "mamb" @merged)
        (should= "SAVED:mSAVED:aSAVED:mSAVED:b" @saved)))))