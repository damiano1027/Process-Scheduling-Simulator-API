package com.pssimulator.domain.process;

import com.pssimulator.domain.time.IntegerTime;
import com.pssimulator.dto.request.ProcessRequestDto;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Processes {
    private final List<Process> processes;

    private Processes(List<Process> processes) {
        this.processes = processes;
    }

    public static Processes from(List<ProcessRequestDto> dtos) {
        List<Process> processes = dtos.stream()
                .map(Process::from)
                .collect(Collectors.toCollection(LinkedList::new));

        return new Processes(processes);
    }

    public static Processes fromProcesses(List<Process> processList) {
        if (processList == null) {
            return new Processes(new LinkedList<>());
        }

        return new Processes(new LinkedList<>(processList));
    }

    public static Processes createEmpty() {
        return new Processes(new LinkedList<>());
    }

    public boolean isEmpty() {
        return processes.isEmpty();
    }

    public List<Process> getArrivedProcessesAt(IntegerTime time) {
        List<Process> arrivedProcesses = new LinkedList<>();

        for (int i = 0; i < processes.size(); i++) {
            Process process = processes.get(i);
            if (process.hasSameArrivalTime(time)) {
                arrivedProcesses.add(process);
                processes.remove(i);
                i--;
            }
        }

        return arrivedProcesses;
    }

    public void calculateResult() {
        processes.forEach(Process::calculateResult);
    }

    public List<Process> getProcesses() {
        return Collections.unmodifiableList(processes);
    }
}
