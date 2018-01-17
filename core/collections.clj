
;; From Notes to https://clojuredocs.org/clojure.core/partition-by:
(defn partition-2
  "Partitions the collection into exactly two [[all-truthy] [all-falsy]]
   collection."
  [pred coll]
  (mapv persistent!
    (reduce
      (fn [[t f] x]
        (if (pred x)
          [(conj! t x) f]
          [t (conj! f x)]))
      [(transient []) (transient [])]
      coll)))

;; My version:
(defn partition-3
  "Partitions the collection into exactly two [[all-truthy] [all-falsy]]
   collection. Almost 2x slower than partition-2 on my Windows 7 machine."
  [pred coll]
  (let [m (group-by pred coll)]
    [(m true) (m false)]))

(defn map-neighbors
  "Applies predicate to every pair of neighbors in collection, returning the results."
  [pred coll]
  (for [i (range (dec (count coll)))]
    (pred (nth coll i)
          (nth coll (inc i)))))
