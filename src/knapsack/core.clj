(ns knapsack.core
  (:require [clojure.math.combinatorics :as c]))



(def limit 10)

(def inputs 
  ["alice" 1 1
   "bob" 2 2
   "charles" 3 3
   "dave" 4 4
   "eileen" 5 5]
)

;; (def inputs
;;   ["luke"        9   150
;;    "anthony"    13    35
;;    "candice"   153   200
;;    "dorothy"    50   160
;;    "puppy"      15    60
;;    "thomas"     68    45
;;    "randal"     27    60
;;    "april"      39    40
;;    "nancy"      23    30
;;    "bonnie"     52    10
;;    "marc"       11    70
;;    "kate"       32    30
;;    "tbone"      24    15
;;    "tommy"      48    10
;;    "uma"        73    40
;;    "grumpkin"   42    70
;;    "dusty"      43    75
;;    "grumpy"     22    80
;;    "eddie"       7    20
;;    "tory"       18    12
;;    "sally"       4    50
;;    "babe"       30    10])

(defrecord Item [name weight value])

(def items (map #(apply ->Item %) (partition 3 inputs)))
items

(def subsets (c/subsets items))
(map :weight (last subsets))
(last subsets)

(count subsets)

(defn weight [e]
  (apply  + (map #(:weight %) e)))

(defn value [e]
  (apply + (map #(:value %) e)))

(defn pack [subsets]
  (let [packings (map-indexed 
                  (fn [i subset] 
                    {:index i :weight (weight subset) :value (value subset)}) subsets)
        packings (filter #(>= limit (:weight %)) packings)
        max-value (apply max (map :value packings))
        optimal-packing (vec (filter #(= max-value (:value  %)) packings))
        indices (map :index optimal-packing)] 
     (map #(nth subsets %) indices)))

(defn output-packing [packing]
  (map #(println (str (:name %) "\t\t") (str  (:weight %) "\t\t") (str (:value %))) packing))

(def p (pack subsets))

(map #(output-packing %) p)
