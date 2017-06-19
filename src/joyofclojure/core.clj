(ns joyofclojure.core
  (:gen-class))

(defn -main []
  (println "Hello, World!"))

(defn sort-parts [work]
  (lazy-seq
    (do
      (println (str "work " work))
      (loop [[part & parts] work]
        (do
          (println (str "seq part" (seq part)))
          (if-let [[pivot & xs] (seq part)]
            (do
              (println (str "pivot " pivot " xs " xs))
              (let [smaller? #(< % pivot)]
                (do 
                  (println (str "recur " (list*
                       (filter smaller? xs)
                       pivot
                       (remove smaller? xs)
                       parts)))
                  (recur (list*
                       (filter smaller? xs)
                       pivot
                       (remove smaller? xs)
                       parts)))))
            (do
              (println (str "when-let parts " parts)
              (when-let [[x & parts] parts]
                (do
                  (println (str "cons " (cons x (sort-parts parts))))
                  (cons x (sort-parts parts))))))))))))

(defn qsort [xs]
  (sort-parts (list xs)))

(defn rand-ints [n]
  (take n (repeatedly #(rand-int n))))

(qsort [4 0 6 2 0 1 9])
