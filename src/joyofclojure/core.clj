(ns joyofclojure.core
  (:gen-class))

(defn -main []
  (println "Hello, World!"))

(defn sort-parts [work]
  (lazy-seq
    (loop [[part & parts] work]
      (if-let [[pivot & xs] (seq part)]
        (let [smaller? #(< % pivot)]
          (recur (list*
                   (filter smaller? xs)
                   pivot
                   (remove smaller? xs)
                   parts)))
        (when-let [[x & parts] parts]
          (cons x (sort-parts parts)))))))

(defn qsort [xs]
  (sort-parts (list xs)))

(defn rand-ints [n]
  (take n (repeatedly #(rand-int n))))
