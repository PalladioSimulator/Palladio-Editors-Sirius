package org.scaledl.architecturaltemplates.completion.constants;

import java.util.HashSet;
import java.util.Set;

import org.palladiosimulator.simulizar.launcher.jobs.LoadPMSModelIntoBlackboardJob;
import org.palladiosimulator.simulizar.launcher.jobs.LoadSDMModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.launcher.jobs.LoadUEModelIntoBlackboardJob;

import de.uka.ipd.sdq.workflow.pcm.jobs.LoadPCMModelsIntoBlackboardJob;

public final class ATPartitionConstants {

    @SuppressWarnings("serial")
    public enum Partition {

        PCM("PCM", LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID, new HashSet<String>() {
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
            }
        }),

        PMS("PMS", LoadPMSModelIntoBlackboardJob.PMS_MODEL_PARTITION_ID, new HashSet<String>() {
            {
                add("pms");
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

        USAGE_EVOLUTION("Usage Evolution", LoadUEModelIntoBlackboardJob.UE_MODEL_PARTITION_ID, new HashSet<String>() {
            {
                add("usageevolution");
                add("dlim");
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
