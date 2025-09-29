/**
 * Slušatelj zahtjeva za uklanjanje playliste iz prikaza (History/Favorites).
 */
public interface HistoryPanelListener {
    /**
     * Traži od kontrolera uklanjanje zadane playliste iz pripadne kolekcije.
     * @param playlist selektirana playlista
     */
    public void favoriteRemoveRequested(Playlist playlist);
}
