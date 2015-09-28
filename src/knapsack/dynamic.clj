(ns knapsack.dynamic)

(defn- get-items 
  "recursive function to pull the indices of selected items for output"
  [kept weights i K indices]
  (if (> i 0) 
    (if (= (aget kept i K) 1)
      (get-items kept weights (dec i) (- K (aget weights i)) (conj indices i))
      (get-items kept weights (dec i) K indices))
    indices))

(defn- pack 
  "the optimization algorithm - impl. of the wiki pseudo-code with changes to allow reporting"
  [v w n W]
  (let [v (int-array v)
        w (int-array w)
        m (make-array Integer/TYPE n W)
        k (make-array Integer/TYPE n W)]

    (doseq [i (range 1 n)]
      (doseq [j (range 0 W)]
        (if (and (<= (aget w i) j) 
                 (<  (aget m (dec i) j) (+ (aget v i) (aget m (dec i) (- j (aget w i))))))  
          
          (do  (aset-int m i j (+ (aget v i) (aget m (dec i) (- j (aget w i)))))
               (aset-int k i j 1))
          
          (do  (aset-int m i j (aget m (dec i) j))
               (aset-int k i j 0)))))

    {:value (aget m (dec n) (dec W))
     :calculated (apply + (map #(nth v %) (get-items k w (dec n) (dec W) ())))
     :items (get-items k w (dec n) (dec W) ())}))

(defn- print-results 
  "print the results per the spec"
  [names weights values results] 
  (printf "packed dolls: \n\n\n")
  (printf "%-10s\t%s\t%s\n" "name" "weight" "value")
  (let [keys (:items results)]
    (doseq [i keys]
      (printf "%-10s\t%d\t%d\n" (nth names i) (nth weights i) (nth values i))))
  (printf "\n\n"))


(defn knapsack 
  "public interface - provide an input vector of the form [name weight value ...] and a limit"
  [inputs limit]
  (let [items (cons '("nil" 0 0) (partition 3 inputs))
        names (mapv first items)
        w (mapv second items)
        v (mapv last items)
        n (count items)
        W (+ 1  limit)
        results (pack v w n W)]
    (print-results names w v results)
    results))



