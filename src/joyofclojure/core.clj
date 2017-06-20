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
          (println (str "(seq part) " (seq part)))
          (if-let [[pivot & xs] (seq part)]
            (let [smaller? #(< % pivot)]
              (do
                (println 
                  (str "recur "
                       (list*
                       (filter smaller? xs)
                       pivot
                       (remove smaller? xs)
                       parts)
                       ))
                (recur (list*
                       (filter smaller? xs)
                       pivot
                       (remove smaller? xs)
                       parts))))
            (when-let [[x & parts] parts]
              (do 
                (println (str "when-let x " x " parts " parts))
                (cons x (sort-parts parts))))))))))

(defn qsort [xs]
  (sort-parts (list xs)))

(defn rand-ints [n]
  (take n (repeatedly #(rand-int n))))

(qsort [2 3 8 0 4 2 0])
