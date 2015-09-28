(ns knapsack.dynamic-test
  (:require [clojure.test :refer :all]
            [knapsack.dynamic :refer :all]))

(deftest test-base 
  (testing "base case from atomic"
    (let [inputs ["luke"        9   150
                  "anthony"    13    35
                  "candice"   153   200
                  "dorothy"    50   160
                  "puppy"      15    60
                  "thomas"     68    45
                  "randal"     27    60
                  "april"      39    40
                  "nancy"      23    30
                  "bonnie"     52    10
                  "marc"       11    70
                  "kate"       32    30
                  "tbone"      24    15
                  "tommy"      48    10
                  "uma"        73    40
                  "grumpkin"   42    70
                  "dusty"      43    75
                  "grumpy"     22    80
                  "eddie"       7    20
                  "tory"       18    12
                  "sally"       4    50
                  "babe"       30    10]
          limit 400
          result (knapsack inputs limit)]      
      (is (= (:value result) 1030))
      (is (= (:items result) '(1 2 3 4 5 7 11 16 17 18 19 21))))))

(deftest test-small
  (testing "from http://www.cs.ucf.edu/~dmarino/ucf/cop3503/lectures/DynProgKnapsack.doc"
    (let [inputs ["A" 4 6
                  "B" 2 4
                  "C" 3 5
                  "D" 1 3
                  "E" 6 9
                  "F" 4 7]
          limit 10
          result (knapsack inputs limit)]      
      (is (= (:value result) 19))
      (is (= (:items result) '(2 3 4 6))))))


(deftest test-p01
  (testing "from https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_01/knapsack_01.html"
    (let [inputs ["A" 23 92
                  "B" 31 57
                  "C" 29 49
                  "D" 44 68
                  "E" 53 60
                  "F" 38 43
                  "G" 63 67
                  "H" 85 84
                  "I" 89 87
                  "J" 82 72]
          limit  165
          result (knapsack inputs limit)]      
      (is (= (:value result) 309))
      (is (= (:items result) '(1 2 3 4 6))))))
