(ns joyofclojure.core
  (:gen-class))

(defn -main []
  (println "Hello, World!"))

(defn sort-parts [work]
  (lazy-seq
    (do
      (println (str "seqs to sort (work) " work))
      (loop [[part & parts] work]
        (do
          (println (str "first seq contains items to sort (seq part) " (seq part)))
          (if-let [[pivot & xs] (seq part)]
            (let [smaller? #(< % pivot)]
              (do
                (println 
                  (str "take first item (pivot = " pivot ") from seq to sort and replace seq to sort with new seqs smaller / larger than pivot "
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
                (println (str "return lowest pivot (" x ") attached to result of sorting remaining seqs of seqs to sort " parts))
                (cons x (sort-parts parts))))))))))

(defn qsort [xs]
  (sort-parts (list xs)))

(defn rand-ints [n]
  (take n (repeatedly #(rand-int n))))

(qsort [2 3 8 0 4 2 0])
