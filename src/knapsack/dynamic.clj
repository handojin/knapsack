(ns knapsack.dynamic)

(def inputs
  ["luke"        9   150
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
   "babe"       30    10])

(def inputs 
  ["A" 4 6
   "B" 2 4
   "C" 3 5
   "D" 1 3
   "E" 6 9
   "F" 4 7])

(defn get-items [kept weights i K indices]
  ;; (println i)
  ;; (println K)
  ;; (println (aget kept i K))
  ;;(println indices)
  (if (> i 0) 
    (if (= (aget kept i K) 1)
      (get-items kept weights (dec i) (- K (aget weights i)) (conj indices i))
      (get-items kept weights (dec i) K indices))
    indices))

(defn pack [v w n W]
  (let [v (int-array v)
        w (int-array w)
        m (make-array Integer/TYPE n W)
        k (make-array Integer/TYPE n W)
        ;;r (make-array Boolean/TYPE n)
        ]

    (doseq [i (range 1 n)]
      (doseq [j (range 0 W)]
        (if (and (<= (aget w i) j) 
                 (<  (aget m (dec i) j) (+ (aget v i) (aget m (dec i) (- j (aget w i))))))  
          
          (do  (aset-int m i j (+ (aget v i) (aget m (dec i) (- j (aget w i)))))
               (aset-int k i j 1))
          
          (do  (aset-int m i j (aget m (dec i) j))
               (aset-int k i j 0)))))
    
    
    ;; (doseq [i (reverse (range n))]
    ;;   (if (true? aget k i W)))

    (clojure.pprint/pprint k)
    {:value (aget m (dec n) (dec W)) 
     :items (get-items k w (dec n) (dec W) ())}
))

(defn print-results [names weights values results] 
  (printf "packed dolls: \n\n\n")
  (printf "%-10s\t%s\t%s\n" "name" "weight" "value")
  (let [keys (:items results)]
    (doseq [i keys]
      (printf "%-10s\t%d\t%d\n" (nth names i) (nth weights i) (nth values i)))))


(defn knapsack [inputs limit]
  (let [items (cons '("nil" 0 0) (partition 3 inputs))
        names (mapv first items)
        w (mapv second items)
        v (mapv last items)
        n (count items)
        W (+ 1  limit)
        results (pack v w n W)]
    (print-results names w v results)
    (:value results)))



