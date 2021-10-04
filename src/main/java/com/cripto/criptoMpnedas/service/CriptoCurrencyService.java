
import com.cripto.criptoMpnedas.domain.CriptoCurrency;
import java.util.List;

public interface CriptoCurrencyService {
    public List<CriptoCurrency> listAllCryptocurrencies();
    public CriptoCurrency getCyroCryptocurrency(Long id);
    public CriptoCurrency createCryptocurrency(CriptoCurrency criptoCurrency);
    public CriptoCurrency updateRank(Long id, Long quoteId);
    public List<CriptoCurrency> findByName(String name);
    public List<CriptoCurrency> findBySymbol(String symbol);

}
