package org.scaledl.architecturaltemplates.completion.constants;

import java.util.HashSet;
import java.util.Set;

import org.palladiosimulator.simulizar.launcher.jobs.LoadMonitorRepositoryModelIntoBlackboardJob;
import org.palladiosimulator.simulizar.reconfiguration.storydiagram.jobs.LoadSDMModelsIntoBlackboardJob;

import de.uka.ipd.sdq.workflow.pcm.jobs.LoadPCMModelsIntoBlackboardJob;

public final class ATPartitionConstants {

    public static Set<String> PCM_FILES = new HashSet<String>() {

        private static final long serialVersionUID = 6737375727319070884L;

        {
            add("repository");
            add("system");
            add("resourceenvironment");
            add("allocation");
            add("usagemodel");
            add("resourcetype");
            add("pcmmeasuringpoint");
            add("measuringpoint");
            add("emfprofile_diagram");
            add("xmi");
            add("usageevolution");
            add("dlim");
        }
    };

    @SuppressWarnings("serial")
    public enum Partition {

        // FIXME Why are the contents of Original PCM and PCM different when being stored with the
        // StoreCompletedModelsJob? [Lehrig]

        // ORIGINAL_PCM("Original PCM",
        // LoadSimuLizarModelsIntoBlackboardJob.ORIGINAL_PCM_MODELS_PARTITION_ID, PCM_FILES),

        PCM("PCM", LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID, PCM_FILES),

        MONITOR_REPOSITORY("Monitor Repository",
                LoadMonitorRepositoryModelIntoBlackboardJob.MONITOR_REPOSITORY_MODEL_PARTITION_ID,
                new HashSet<String>() {

                    {
                        add("monitorrepository");
                    }
                }),

        EVENT_MIDDLEWARE("Event Middleware", LoadPCMModelsIntoBlackboardJob.EVENT_MIDDLEWARE_PARTITION_ID,
                new HashSet<String>() {

                    {
                        add("repository");
                    }
                }),

        RMI_MIDDLEWARE("RMI Middleware", LoadPCMModelsIntoBlackboardJob.RMI_MIDDLEWARE_PARTITION_ID,
                new HashSet<String>() {

                    {
                        add("repository");
                    }
                }),

        SDM("SDM", LoadSDMModelsIntoBlackboardJob.SDM_MODEL_PARTITION_ID, new HashSet<String>() {

            {
                add("sdm");
            }
        });

        private final String name;
        private final String partitionId;
        private final Set<String> fileNames;

        private Partition(final String name, final String partitionId, final Set<String> fileNames) {
            this.name = name;
            this.partitionId = partitionId;
            this.fileNames = fileNames;
        }

        public String getName() {
            return this.name;
        }

        public String getPartitionId() {
            return this.partitionId;
        }

        public Set<String> getFileNames() {
            return this.fileNames;
        }
    }

}
