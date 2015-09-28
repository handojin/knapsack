(ns knapsack.dynamic)

(def inputs
  ["nil"         0     0
   "luke"        9   150
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
   "babe"       30    10
   ])

(def inputs 
  ["nil" 0 0
   "A" 4 6
   "B" 2 4
   "C" 3 5
   "D" 1 3
   "E" 6 9
   "F" 4 7])

(defn pack [v w n W]
  (println v)
  (println w)
  (println n)
  (println W)

  (let [v (int-array v)
        w (int-array w)
        m (make-array Integer/TYPE n W)
        k (make-array Boolean/TYPE n)]
    ;;(clojure.pprint/pprint k)
    (doseq [i (range 1 n)]
      (doseq [j (range 0 W)]
        (if (and (<= (aget w i) j) 
                 (<  (aget m (dec i) j) (+ (aget v i) (aget m (dec i) (- j (aget w i))))))  
          (do  (aset-int m i j 
                         ;;(max (aget m (dec i) j)) 
                         (+ (aget v i) (aget m (dec i) (- j (aget w i)))))
               (aset-boolean k i true))
          
          (do  (aset-int m i j (aget m (dec i) j))
               (aset-boolean k i false)))
        ))
    ;;(clojure.pprint/pprint m)
    ;;(clojure.pprint/pprint k)
    {:value (aget m (dec n) (dec W)) 
     :items (vec (filter number? (map-indexed (fn [i v] (if (true? v) i)) k)))}
))

(defn knapsack [inputs limit]
  (let [items (partition 3 inputs)
        names (mapv first items)
        w (mapv second items)
        v (mapv last items)
        n (count items)
        W (+ 1  limit)]
    (pack v w n W)))


(knapsack inputs 400)

