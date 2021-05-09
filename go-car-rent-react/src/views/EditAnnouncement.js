import AnnouncementForm from "../components/AnnouncementForm";

export default function EditAnnouncement(props) {
    return(
        <AnnouncementForm edit={true} announcementId={props.match.params.id}/>
    )
}