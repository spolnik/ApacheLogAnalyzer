using System;

namespace DataAccessLayer
{
    public class LogStatistic<K,V>
    {
        public LogStatistic(K key, V value)
        {
            Key = key;
            Value = value;
        }

        public K Key { get; private set; }
        public V Value { get; private set; }
    }
}