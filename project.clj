(defproject knapsack "0.0.1-SNAPSHOT"
  :description "brute force and dynamic solvers for the 0/1 Knapsack Problem"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/math.combinatorics "0.1.1"]]
  :jvm-opts [ "-Xms4G" "-Xmx4G" "-XX:-UseConcMarkSweepGC"])
