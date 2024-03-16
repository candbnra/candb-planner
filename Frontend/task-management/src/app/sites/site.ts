import { Workstream } from "../workstreams/workstream";


export class Site {
    id: number = 0;
    name: string = '';
    code: string = '';
    region: string = '';
    zone: string = '';
    area: string = '';
    status: string = '';

    porteurProspection: string = '';
	bailleur: string = '';
	programme: string = '';
	projet: string = '';
	moeProjet: string = '';
	agenceMoe: string = '';
	equipePlanifDsor: string = '';
	cdpPlanifDsor: string = '';
	equipePatrimoineRegion: string = '';
	acteurPatrimoineRegion: string = '';
	rdpRegion: string = '';
	equipeRespSite: string = '';
	respSite: string = '';
	ingeRadio: string = '';
	eb: string = '';
    acteurProspection: string = '';
    nbProspects: number = 0;
    workstreams: Workstream[] = [];
}
