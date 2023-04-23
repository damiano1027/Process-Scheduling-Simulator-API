package com.pssimulator.domain.queue;

import com.pssimulator.domain.process.Process;
import com.pssimulator.domain.process.Processes;
import com.pssimulator.domain.time.IntegerTime;

public abstract class ReadyQueue {
    public abstract boolean isEmpty();

    public abstract void addArrivedProcessesFrom(Processes processes, IntegerTime currentTime);

    public abstract Process getNextProcess();

    public abstract void increaseWaitingTimeOfProcesses();
}
