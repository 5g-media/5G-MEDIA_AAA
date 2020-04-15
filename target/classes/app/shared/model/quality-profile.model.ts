export interface IQualityProfile {
    id?: number;
    name?: string;
    minBitrateKbps?: number;
    maxBitrateKbps?: number;
    minCompressionLevel?: number;
    maxCompressionLevel?: number;
    meanEntropyTI?: number;
    meanEntropySI?: number;
    minExpectedQOE?: number;
    maxExpectedQOE?: number;
}

export class QualityProfile implements IQualityProfile {
    constructor(
        public id?: number,
        public name?: string,
        public minBitrateKbps?: number,
        public maxBitrateKbps?: number,
        public minCompressionLevel?: number,
        public maxCompressionLevel?: number,
        public meanEntropyTI?: number,
        public meanEntropySI?: number,
        public minExpectedQOE?: number,
        public maxExpectedQOE?: number
    ) {}
}
